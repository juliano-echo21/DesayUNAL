function eliminar(id){
    swal({
        title: "¿Está seguro de eliminar?",
        text: "Una vez eliminado, no podrá recuperar la información",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    })
        .then((OK) => {
            if (OK) {
                $.ajax({
                    url:"/eliminar/"+id,
                    success: function(res){
                        Console.log(res);
                    }
                });
                swal("El producto ha sido eliminado", {
                    icon: "success",
                }).then((ok)=>{
                    if(ok){
                        location.href="/listar";
                    }
                });
            } else {
            }
        });
}

function agregar(){
    swal("Agregado al carrito", {
        icon: "success",
    }).then((ok)=>{
        if(ok){
        }
    });
}

function compraExitosa(){
    swal("Compra exitosa!!!", {
        icon: "success",
    }).then((ok)=>{
        if(ok){
            parent.location.href="generarCompra";
        }else{       
            parent.location.href="generarCompra";
        }
    });

}

function loguearse(){
    swal("Debe iniciar sesión", {
        icon: "error",
    }).then((ok)=>{
        if(ok){
        }
    });
}


function carritoVacio(){
    swal("No hay productos en el carrito", {
        icon: "error",
    }).then((ok)=>{
        if(ok){
        }
    });
}

$(document).ready(function(){
    $("tr #btnDelete").click(function (){
    
        var idp = $(this).parent().find("#idp").val();
        swal({
            title: "¿Está seguro de eliminar?",
            icon: "warning",
            buttons: true,
            dangerMode: true,
        }).then((OK) => {
                if (OK) {
                    eliminar(idp);
                    swal("El producto ha sido eliminado", {
                        icon: "success",
                    }).then((ok)=>{
                        if(ok){
                            parent.location.href="carrito";
                        }
                    });
                } else {
                }
            });
    });

    function eliminar(idp){
        var url="eliminarDelCarrito";
        $.ajax({
            type: 'POST',
            url: url,
            data: "idp="+idp,
            success: function(data, textStatus, jqXHR){
            }
        })
    }

    $("tr #cantidad").change(function(){
        var idp = $(this).parent().find("#idpro").val();
        var cantidad = $(this).parent().find("#cantidad").val();
        var url = "actualizarCarrito";
        $.ajax({
            type: 'POST',
            url: url,
            data: "idp="+idp+"&cantidad="+cantidad,
            success: function(data, textStatus, jqXHR){
            }
        });
        
        parent.location.href="carrito";
    })
});

function cancelarCompra(){
    swal({
        title: "¿Está seguro de cancelar la compra?",
        text: "El carrito será eliminado",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    })
        .then((OK) => {
            if (OK) {
                parent.location.href="vaciarCarrito";
            } else {
            }
        });
}
