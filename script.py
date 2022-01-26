from unittest import result
import mysql.connector

mydb = mysql.connector.connect(
    host = "localhost",
    user = "root",
    password = "hu1ch0l0_21",
    database = "desayunal"
)

cursor = mydb.cursor()

sql = "select * from usuario;"
cursor.execute(sql)
resultado = cursor.fetchall()

for i in resultado:
    print(i)
