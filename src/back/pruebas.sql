USE Proyecto;

select count(*)
from usuario
where id_nombre = 'Usuario1'
  and contraseña = 'Contraseña1'
