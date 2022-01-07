package com.example.desayunal.InterfacesServicios;

import java.util.List;
import com.example.desayunal.model.Usuario;
import com.example.desayunal.web.dto.RegistroUsuarioDto;

public interface IServicioUsuario {

    Usuario save(RegistroUsuarioDto registroDto);
    List<Usuario> userExists(String userName);

}
