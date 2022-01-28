from unittest import result
import mysql.connector
import json
import random


data_file = open("generator/data.json",)
data = json.load(data_file)

mydb = mysql.connector.connect(
    host = "localhost",
    user = "root",
    password = "hu1ch0l0_21",
    database = "desayunal"
)

cursor = mydb.cursor()

def crearUsuario():
    estado = "Activo"
    role = "Cliente"
    vals = []
    for i in range (2000): ##crear 2000 usuarios

        user = data["random_names"][i]
        password = random.choice(data["random_passwords"])
        tupla = (user,password,estado,role)
        vals.append(tupla)
        
        sql = "INSERT INTO usuario (user_name, password, estado, role) VALUES (%s, %s, %s, %s);"
        cursor.execute(sql, tupla)

    mydb.commit()
    print(cursor.rowcount, "insertados.")


def generarCantidades(n):
    l =[]
    for i in range(n):
        l.append( random.randint(1,10))
    return l

def traerProductos():
    sql= "SELECT * FROM producto;"
    cursor.execute(sql)
    return  cursor.fetchall()



def crearOrdenes():

    lista_productos = traerProductos()
    print("\n lista de todos: \n "+str(lista_productos))

    sql = "SELECT COUNT(*) FROM ORDEN;"
    cursor.execute(sql) 
    n_ord = cursor.fetchall()[0][0]

    
    for i in range(n_ord+1, n_ord + 20000): #30.000 ordenes
        precio = 0
        subtotales = []
        fecha = "{0}/{1}/2021".format(random.randint(1,28),random.randint(1,12))
        estado = "Entregado"
        usuario = random.randint(1,2001)
        hora_entrega = "12:12:12"
        hora_pedido = "11:59:10"

        #n_productos = 2
        n_productos = random.randint(1,8) #numero de productos
        cantidades = generarCantidades(n_productos)

        #produc_compra = random.sample(lista_productos,2)  #asumiendo que hay 25 productos seleccionar n_productos
        produc_compra = random.sample(lista_productos,n_productos)  

        print("\n productos a comprar \n "+str(produc_compra))


        for k in range(n_productos):
            precio+= cantidades[k]* produc_compra[k][5]
            subtotales.append(cantidades[k]* produc_compra[k][5])


        sql = "INSERT INTO orden (estado,fecha,hora_entrega,hora_pedido,precio,fk_usuarioid) VALUES (%(estado)s,%(fecha)s,%(hora_entrega)s,%(hora_pedido)s,%(precio)s,%(usuario)s)"
        
        values = {
                "estado": estado,
                "fecha":fecha,
                "hora_entrega":hora_entrega,
                "hora_pedido":hora_pedido,
                "precio":precio,
                "usuario":usuario
                }
        
        #values = (estado,fecha, hora_entrega, hora_pedido,int(precio), int(usuario))
        cursor.execute(sql,values)
        mydb.commit()


        crearDetalleOrden(i,cantidades,produc_compra,subtotales)


def  crearDetalleOrden(i,cantidades,produc_compra,subtotales):

    for k in range(len(subtotales)):
        values = {
        "cantidad_producto" : cantidades[k],
        "subtotal" : subtotales[k],
        "fk_ordenid" : i,
        "productoid" : produc_compra[k][0]
        }

        sql = "INSERT INTO DETALLES_ORDEN (cantidad_producto,subtotal,fk_ordenid,fk_productoid)  VALUES (%(cantidad_producto)s,%(subtotal)s,%(fk_ordenid)s,%(productoid)s);"
        cursor.execute(sql,values)
        mydb.commit()




crearUsuario()
crearOrdenes()
#pruebas()


