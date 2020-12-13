package com.example.crud176;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface MyDao {

    @Insert
    public void inserirUsuario(Usuario usuario);

    @Delete
    public void deletarUsuario(Usuario usuario);

    @Query("select * from usuarios")
    public List<Usuario> listarUsuarios();
}
