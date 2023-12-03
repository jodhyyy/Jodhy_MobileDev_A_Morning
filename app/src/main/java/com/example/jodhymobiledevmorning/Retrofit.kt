package com.example.jodhymobiledevmorning

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jodhymobiledevmorning.databinding.FragmentRetrofitBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Retrofit : Fragment() {
    private var _binding: FragmentRetrofitBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRetrofitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val jodhy = view.findViewById<RecyclerView>(R.id.rv_character)

        ApiConfig.getService().getJodhy().enqueue(object : Callback<ResponseJodhy> {
            override fun onResponse(call: Call<ResponseJodhy>, response: Response<ResponseJodhy>) {
                if (response.isSuccessful) {
                    val responseJodhy = response.body()
                    val dataJodhy = responseJodhy?.results
                    val jodhyAdapter = JodhyAdapter(dataJodhy as List<ResultsItem>)
                    jodhy.apply {
                        layoutManager = LinearLayoutManager(binding.root.context)
                        adapter = jodhyAdapter
                    }
                }
            }

            override fun onFailure(call: Call<ResponseJodhy>, t: Throwable) {
                Toast.makeText(requireContext(), t.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
