package com.example.app_sudamericana.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.example.app_sudamericana.EditarPerfilActivity
import com.example.app_sudamericana.PruebaActivity
import com.example.app_sudamericana.R
import com.example.app_sudamericana.enviroments.Credentials
import com.google.android.material.textfield.TextInputEditText
import org.w3c.dom.Text

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PerfilFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PerfilFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var spInstance: SharedPreferences;
    private lateinit var nameProfile: TextView
    private lateinit var emailProfile: TextView
    var linearLayaoutEditPerfil: LinearLayout? = null

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        this.spInstance = requireActivity().getSharedPreferences(
            Credentials.NAME_PREFERENCES,
            Context.MODE_PRIVATE
        );

        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_perfil, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        linearLayaoutEditPerfil = view.findViewById(R.id.linearLayaoutEditPerfil)

        linearLayaoutEditPerfil?.setOnClickListener {
            context?.let { safeContext ->
                val intent = Intent(safeContext, EditarPerfilActivity::class.java)
                startActivity(intent)
            }
        }

        // setear datos a los textview
        nameProfile = view.findViewById(R.id.textViewUsername);
        emailProfile = view.findViewById(R.id.textViewEmail);

        val emailText = this.spInstance.getString(Credentials.USER_EMAIL, "email vacio");
        val nameText = this.spInstance.getString(Credentials.USER_FIRSTNAME, "nombre vacio");

        nameProfile.setText(nameText);
        emailProfile.setText(emailText);
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PerfilFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}