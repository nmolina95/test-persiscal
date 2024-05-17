# Punto 3, pasos:

- Parados en la branch correspondiente a DEV, revisamos las últimas versiones subidas.

git log

- Cada resultado de la lista de commits se verá así:

commit 01dcc2b0a5383aa86fc955058bc5ed328e4a066c (HEAD -> master, origin/master, origin/HEAD)
Author: Ignacio Molina <62192384+nmolina95@users.noreply.github.com>
Date:   Sat Jun 5 15:08:11 2021 -0300

- Tomamos el código hash del commit y usamos el comando revert, incluyendo al final el código de la versión a la cual querramos regresar

git revert 01dcc2b0a5383aa86fc955058bc5ed328e4a066c

- Una vez hecho esto, pushearemos el nuevo commit a la branch DEV revirtiendo así los cambios de la última versión

git push origin DEV

- Luego nos paramos en la rama master, y le hacemos un merge proveniente de la branch DEV

git checkout master
git merge DEV

- Le agregamos el tag correspondiente a la versión, y lo pusheamos en master

git tag v1.2.0
git push origin v1.2.0

- Para llevarlo a producción se debería usar alguna herramienta de despliegue elegida, pero con estos pasos se deja lista la branch.