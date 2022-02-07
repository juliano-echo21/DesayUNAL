package com.example.desayunal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import javax.transaction.Transactional;
import com.example.desayunal.model.Orden;
import com.example.desayunal.model.Producto;
import com.example.desayunal.services.ServicioOrden;
import com.example.desayunal.services.ServicioProducto;
import com.example.desayunal.services.ServicioUsuario;
import com.example.desayunal.web.dto.RegistroUsuarioDto;
import com.example.desayunal.model.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;



@SpringBootTest
class DesayUnalApplicationTests{
    @Autowired
    ServicioProducto servicioProducto;
    @Autowired
    ServicioUsuario servicioUsuario;
    @Autowired  
    RegistroUsuarioDto registroUsuarioDto;
    @Autowired
    ServicioOrden servicioOrden;

    @Test
    @Transactional
    void testGuardarProducto(){
        Producto producto =new Producto(
          0, 
          "DesayunoPrueba", 
          "Producto para realizar pruebas", 
          "Oferta",
          5000,
          "Desayunos"
        );
        servicioProducto.guardar(producto,false);
        
        assertTrue(servicioProducto.productoExiste("DesayunoPrueba")); 
    }
    @Test
    @Transactional
    void testActualizarProducto(){
        Producto producto =new Producto(
                0, 
                "DesayunoPrueba", 
                "Producto para realizar pruebas", 
                "Oferta",
                5000,
                "Desayunos"
              );
        servicioProducto.guardar(producto,false);
        String nombreNuevo ="Iphone 13 pro max";
        producto.setNombre(nombreNuevo);
        servicioProducto.guardar(producto,true);

        assertTrue(servicioProducto.productoExiste(nombreNuevo)); 
        
    }
    @Test
    @Transactional
    void testListarProductos(){
        int productosInciales = servicioProducto.totalProductos();
        Producto producto =new Producto(
            0, 
            "DesayunoPrueba", 
            "Producto para realizar pruebas", 
            "Oferta",
            5000,
            "Desayunos"
          );
        servicioProducto.guardar(producto,false);
        int productosFinales = servicioProducto.totalProductos();
        Boolean resultadoTest=false;
        if(productosInciales<=productosFinales){
            resultadoTest=true;
        }
        
        assertTrue(resultadoTest);
    }
    @Test
    @Transactional
    void testBuscarProducto(){
        Producto producto =new Producto(
            0, 
            "DesayunoPrueba1", 
            "Producto para realizar pruebas", 
            "Oferta",
            5000,
            "Desayunos"
          );
        servicioProducto.guardar(producto,false);
        Producto resultadoBusqueda=servicioProducto.listarId(producto.getId()).get();
        Boolean resultadoTest=false;
        if(resultadoBusqueda!=null)
            resultadoTest=true;
        
        assertTrue(resultadoTest);  
    }
    @Test
    @Transactional
    void testEliminarProducto(){
        Producto producto =new Producto(
            0,
            "DesayunoPrueba", 
            "Producto para realizar pruebas", 
            "Oferta",
            5000,
            "Desayunos"
          );

        servicioProducto.guardar(producto,false);
        int idEliminar= producto.getId();           
        servicioProducto.eliminar(idEliminar);

        assertFalse(servicioProducto.productoExiste("DesayunoPrueba"));
    }
    @Test
    @Transactional
    void testLoggeadoCorrecto(){
        RegistroUsuarioDto usuario =new RegistroUsuarioDto(
        "UserPruebas", 
        "1234", 
        "Activo", 
        "Cliente"
        );
        servicioUsuario.save(usuario);
        RegistroUsuarioDto usuario2 =new RegistroUsuarioDto(
          "UserPruebas", 
          "1234", 
          "Activo", 
          "Cliente"
        );
        String contraseña=servicioUsuario.userPassword(usuario.getUserName());
        if(contraseña.equals(usuario2.getPassword()))
          servicioUsuario.actualizarEstadoLogin(true);
      
        assertTrue(servicioUsuario.getEstadoLogin());
    }
    @Test
    @Transactional
    void testLoggeadoIncorrecto(){
        RegistroUsuarioDto usuario =new RegistroUsuarioDto(
          "UserPruebas", 
          "1234", 
          "Activo", 
          "Cliente"
        );      
        servicioUsuario.save(usuario);
        RegistroUsuarioDto usuario2 =new RegistroUsuarioDto(
          "UserPruebas", 
          "12345", 
          "Activo", 
          "Cliente"
        );
        String contraseña=servicioUsuario.userPassword(usuario.getUserName());
        if(contraseña.equals(usuario2.getPassword()))
          servicioUsuario.actualizarEstadoLogin(true);

        assertFalse(servicioUsuario.getEstadoLogin());
    }
    @Test
    @Transactional
    void testCompraCorrecta(){
        RegistroUsuarioDto usuario =new RegistroUsuarioDto(
                "UserPruebas", 
                "1234", 
                "Activo", 
                "Cliente"
              );      
        servicioUsuario.save(usuario);
        Usuario usuario2 =new Usuario(
          "UserPruebas", 
          "1234", 
          "Activo", 
          "Cliente"
        );      
        Orden orden=new Orden(
            26,
            10,
            2001,
            07,
            "00",
            "07:20 am",
            900000,
            " ",
            usuario2
        );
        servicioOrden.guardar(orden);
         Boolean resultadoTest=false;
         List<Orden> ordenes=servicioOrden.listarPorUsuario(usuario2);
         int idOrden=ordenes.get(0).getId();
          if(idOrden==orden.getId())
              resultadoTest=true;

         assertTrue(resultadoTest);
    }
    @Test 
    @Transactional
    void testSingUpCorrecto(){
        RegistroUsuarioDto usuario =new RegistroUsuarioDto(
          "UserPruebas", 
          "1234,1234", 
          "Activo", 
          "Cliente"
        );
        String[] passwords = usuario.getPassword().split(",");
        Boolean resultadoTest=false;
        if(passwords[0].equals(passwords[1])) {  
          usuario.setPassword(passwords[0]); 
          servicioUsuario.save(usuario);
          resultadoTest=true;
        }   
        else{
          resultadoTest=false;
        }
    
        assertTrue(resultadoTest);
    } 
    @Test
    @Transactional
    void testSingUpContraseña(){
        RegistroUsuarioDto usuario =new RegistroUsuarioDto(
          "UserPruebas", 
          "1234,1345", 
          "Activo", 
          "Cliente"
        );
        String[] passwords = usuario.getPassword().split(",");
        Boolean resultadoTest=false;
        if(passwords[0].equals(passwords[1])) {  
          usuario.setPassword(passwords[0]); 
          servicioUsuario.save(usuario);
          resultadoTest=true;
        }   
        else{
          resultadoTest=false;
        }
    
        assertFalse(resultadoTest);
    }
    @Test
    @Transactional
    void testSingUpUsuario(){
        RegistroUsuarioDto usuario =new RegistroUsuarioDto(
          "UserPruebas", 
          "1234",
          "Activo", 
          "Cliente"
        );        
        servicioUsuario.save(usuario);
        RegistroUsuarioDto usuario2 =new RegistroUsuarioDto(
          "UserPruebas", 
          "1234",
          "Activo", 
          "Cliente"
        );
        Boolean resultadoTest=false;
        Usuario resultadoBusqueda =servicioUsuario.buscarUserName(usuario2.getUserName()).get(0);
        if(resultadoBusqueda==null){          
           servicioUsuario.save(usuario2);
           resultadoTest=true;
        }

        assertFalse(resultadoTest);
    }
}
