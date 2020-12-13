package com.example.crud176;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdicionarUsuarioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdicionarUsuarioFragment extends Fragment {

    EditText editTextId_, editTextNome_, editTextEmail_;
    Button btSalvarUsuario_;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AdicionarUsuarioFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AdicionarUsuarioFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AdicionarUsuarioFragment newInstance(String param1, String param2) {
        AdicionarUsuarioFragment fragment = new AdicionarUsuarioFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_adicionar_usuario, container, false);

        btSalvarUsuario_ = view.findViewById(R.id.btSalvarUsuario);
        editTextId_ = view.findViewById(R.id.editTextId);
        editTextNome_ = view.findViewById(R.id.editTextNome);
        editTextEmail_ = view.findViewById(R.id.editTextEmail);

        btSalvarUsuario_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Usuario usuario = new Usuario();
                usuario.setId(Integer.parseInt(editTextId_.getText().toString()));
                usuario.setEmail(editTextNome_.getText().toString());
                usuario.setNome(editTextEmail_.getText().toString());

                MainActivity.myAppDatabase.myDao().inserirUsuario(usuario);

                editTextId_.setText("");
                editTextNome_.setText("");
                editTextEmail_.setText("");

                Toast.makeText(getContext(), "Usuário adicionado", Toast.LENGTH_SHORT).show();

            }
        });


        return view;
    }
}