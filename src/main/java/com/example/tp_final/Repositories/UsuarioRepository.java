package com.example.tp_final.Repositories;

import com.example.tp_final.Entidades.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends BaseRepository<Usuario,Long> {
}
