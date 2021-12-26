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