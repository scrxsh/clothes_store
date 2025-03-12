# Sistema de Gestión de Ventas en Java con SpringBoot
En el siguiente repositorio se encuentra el codigo fuente del proyecto "Sistema de gestión de ventas con analisis predictivo y machine learning", que esta en desarrollo en el lenguaje Java con su aclamado framework "SpringBoot", con el añadido de una red neuronal.

# Modulos del proyecto:


- **Inventario ->** Apartado donde se registran, se eliminan y se modifican las existencias.
- **Usuarios ->** Apartado donde se registran los usuarios tanto de vendedores, proveedores y los clientes finales.
- **Ventas ->**  Apartado donde se registran las compras del usuario y se genera su respectiva facturación electronica de acuerdo a las normas contables en Colombia.
- **Modelo de ventas esperadas ->** Modulo donde se genera un modelo de red neuronal que determine la cantidad de ventas de cierto producto de acuerdo a datos historicos.  

# Diseño inicial
**Aplicación web cuyas funcionalidades son:**

- Conexión mediante BD en el gestor de BD de MYSQL WORKBENCH 8.0 CE para el registro de Inventario, Usuarios, Proveedores y Usuarios.
- En la parte del Backend se creará una API REST con el framework de JAVA, Spring Boot y sus dependencias: Spring Web, Spring Data JPA, MYSQL Driver y Lombok.
- En la parte del Fronted se consumira el API con ayuda de la herramienta POSTMAN, posteriormente se hará la conexión entre frontend y backend con JavaScript, finalmente se hará el diseño de la web con HTML, CSS y el Framework tailwindcss. 
- Control y registro de Ventas. (Con datos como valor, fecha, productos)
- Control y registro de Usuarios. (Con datos de nombre, correo, telefono)
- Control y registro de Inventario. (Con datos como tipo, talla, codigo, tela).
  
  

  
  

  
