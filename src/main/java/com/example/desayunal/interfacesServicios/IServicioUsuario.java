package com.example.desayunal.interfacesServicios;

import java.util.List;
import com.example.desayunal.model.Usuario;
import com.example.desayunal.web.dto.RegistroUsuarioDto;

public interface IServicioUsuario {

    Usuario save(RegistroUsuarioDto registroDto);
    String userPassword(String userName);

}
