package com.atakanturgut.foodapp.view

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import androidx.room.Room
import com.atakanturgut.foodapp.databinding.FragmentDetailBinding
import com.atakanturgut.foodapp.model.Food
import com.atakanturgut.foodapp.roomdb.FoodDAO
import com.atakanturgut.foodapp.roomdb.FoodDatabase
import com.google.android.material.snackbar.Snackbar
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.ByteArrayOutputStream

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    // Activity Launcher
    private lateinit var permissionLauncher : ActivityResultLauncher<String>
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>

    // Görsel Kaynakları
    private var secilenGorselUri : Uri? = null
    private var secilenBitmap : Bitmap? = null // görsel türe çevirebilmek için kullanılır

    // Database
    private lateinit var db : FoodDatabase
    private lateinit var foodDao : FoodDAO

    // RxJava
    private val mDisposable  =  CompositeDisposable()

    private var secilenTarif : Food? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        registerLauncher()

        db = Room.databaseBuilder(requireContext(), FoodDatabase::class.java, "Tarifler")
            //.allowMainThreadQueries()
            .build()

        foodDao = db.foodDao()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imageView.setOnClickListener { gorselSec(it) }
        binding.buttonKaydet.setOnClickListener { kaydet(it) }
        binding.buttonSil.setOnClickListener { sil(it) }

        arguments?.let {
            val bilgi = DetailFragmentArgs.fromBundle(it).bilgi

            if (bilgi == "yeni") {
                // Yeni tarif eklenecek
                secilenTarif = null

                binding.buttonSil.isEnabled = false
                binding.buttonKaydet.isEnabled = true

                binding.editTextIsim.setText("")
                binding.editTextMalzeme.setText("")
                binding.editTextTarif.setText("")
            } else {
                // Eskiden eklenmiş tarif görünecek
                binding.buttonSil.isEnabled = true
                binding.buttonKaydet.isEnabled = false

                val id = DetailFragmentArgs.fromBundle(it).id
                mDisposable.add(
                    foodDao.findById(id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::handleResponse)
                )
            }

        }
    }

    private fun handleResponse(food: Food) {
        val bitmap = BitmapFactory.decodeByteArray(food.gorsel, 0, food.gorsel.size)
        binding.imageView.setImageBitmap(bitmap)

        binding.editTextIsim.setText(food.isim)
        binding.editTextMalzeme.setText(food.malzeme)
        binding.editTextTarif.setText(food.tarif)

        secilenTarif = food
    }

    fun kaydet(view: View) {
        val isim = binding.editTextIsim.text.toString()
        val malzeme = binding.editTextMalzeme.text.toString()
        val tarif = binding.editTextTarif.text.toString()

        if (secilenBitmap != null) {
            val kucukBitmap = kucukBitmapOlustur(secilenBitmap!!, 300)
            val outputStream = ByteArrayOutputStream()

            kucukBitmap.compress(Bitmap.CompressFormat.PNG, 80, outputStream)
            val byteDizisi = outputStream.toByteArray()

            val yemek = Food(isim, malzeme, tarif, byteDizisi)

            //foodDao.insert(yemek)
            // Threading - RxJava
            mDisposable.add(
                foodDao.insert(yemek)                               // işlemi yap
                    .subscribeOn(Schedulers.io())                   // işlemi arka planda yap
                    .observeOn(AndroidSchedulers.mainThread())      // işlemi ön planda göster
                    .subscribe(this::handleResponseForInsert)       // işlem bitince metodu çalıştır
            )
        }
    }

    private fun handleResponseForInsert() {
        // bir önceki fragment gönüşü
        val action = DetailFragmentDirections.actionDetailFragmentToListFragment()
        Navigation.findNavController(requireView()).navigate(action)
    }

    fun sil(view: View) {
        if (secilenTarif != null) {
            mDisposable.add(
                foodDao.delete(food = secilenTarif!!)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::handleResponseForInsert)
            )
        }
    }

    fun gorselSec(view: View) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_MEDIA_IMAGES) != PackageManager.PERMISSION_GRANTED) {
                // izin verilmemiş - izin istenmesi gerekiyor
                if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), Manifest.permission.READ_MEDIA_IMAGES)) {
                    // snackbar gösterilmesi lazım, kullanıcıdan neden izin istediğimizi bir kez daha söyleyerek izin istememiz lazım
                    Snackbar.make(view, "Galeriye ulaşarak görsel seçmemiz lazım!", Snackbar.LENGTH_INDEFINITE).setAction(
                        "İzin Ver", View.OnClickListener {
                            // izin istenecek
                            permissionLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES)
                        }
                    ).show()
                } else {
                    // izin isteyeceğiz
                    permissionLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES)
                }
            } else {
                // izin verilmiş - galeriye gidebilirim
                val intentToGallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                activityResultLauncher.launch(intentToGallery)
            }
        } else {
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                // izin verilmemiş - izin istenmesi gerekiyor
                if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    // snackbar gösterilmesi lazım, kullanıcıdan neden izin istediğimizi bir kez daha söyleyerek izin istememiz lazım
                    Snackbar.make(view, "Galeriye ulaşarak görsel seçmemiz lazım!", Snackbar.LENGTH_INDEFINITE).setAction(
                        "İzin Ver", View.OnClickListener {
                            // izin istenecek
                            permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                        }
                    ).show()
                } else {
                    // izin isteyeceğiz
                    permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                }
            } else {
                // izin verilmiş - galeriye gidebilirim
                val intentToGallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                activityResultLauncher.launch(intentToGallery)
            }
        }
    }

    private fun registerLauncher() {
        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                result ->
            if (result.resultCode == AppCompatActivity.RESULT_OK) {
                val intentFromResult = result.data

                if (intentFromResult != null) {
                    secilenGorselUri = intentFromResult.data

                    try {
                        if (Build.VERSION.SDK_INT >= 28) {
                            val source = ImageDecoder.createSource(requireActivity().contentResolver, secilenGorselUri!!)
                            secilenBitmap = ImageDecoder.decodeBitmap(source)
                            binding.imageView.setImageBitmap(secilenBitmap)
                        } else {
                            secilenBitmap = MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, secilenGorselUri)
                            binding.imageView.setImageBitmap(secilenBitmap)
                        }
                    } catch (e: Exception) {
                        println(e.localizedMessage)
                    }
                }
            }
        }

        permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
                result ->
            if (result) {
                // izin verildi, galeriye gidilebilir
                val intentToGallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                activityResultLauncher.launch(intentToGallery)
            } else {
                // izin verilmedi
                Toast.makeText(requireContext(), "İzin verilmedi!", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun kucukBitmapOlustur(kullanicininSectigiBitmap : Bitmap, maximumBoyut : Int) : Bitmap {
        var width = kullanicininSectigiBitmap.width
        var height = kullanicininSectigiBitmap.height

        val bitmapOrani : Double = width.toDouble() / height.toDouble()

        if (bitmapOrani > 1) {
            // görsel yatay
            width = maximumBoyut

            val kisaltilmisYukseklik = width / bitmapOrani
            height = kisaltilmisYukseklik.toInt()
        } else {
            // görsel dikey
            height = maximumBoyut

            val kisaltilmisGenislik = height * bitmapOrani
            width = kisaltilmisGenislik.toInt()
        }

        return Bitmap.createScaledBitmap(kullanicininSectigiBitmap, width, height, true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

        // Threading - RxJava
        mDisposable.clear()
    }
}