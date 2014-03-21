@echo off

pushd %cd%

set clean="yes"
set jar="yes"
set verbose="no"
set netbeansClasses="E:\NetBeans 8.0\platform\modules\ext\swing-layout-1.0.4.jar"

rem parsage des arguments
for %%a in (%*) do (
    if [%%a]==[-noclean] set clean="no"
    if [%%a]==[-nojar] set jar="no"
    if [%%a]==[-v] set verbose="yes"
    if [%%a]==[-h] (
        echo.
        echo Available parameters : -noclean, -nojar, -v, -h
        echo.
        echo -noclean : Don't clean the target directory before building
        echo -nojar   : Don't create jar files
        echo -v       : Verbose debug infos
        echo -h       : Display this help
        goto eof
    )
)

if %clean%=="no" goto compile

echo.
echo Nettoyage...
cd target
IF EXIST *.jar del /Q /F *.jar
IF EXIST classes rmdir /s /q classes
IF NOT EXIST classes mkdir classes
cd ..

:compile
echo.
echo Compilation du package authenticate...
set compile=-s src -d target/classes src/be/beneterwan/gestiongare/authenticate/*.java
if %verbose%=="yes" (
    javac -verbose %compile%
) else (
    javac %compile%
)
echo Compilation du package logins...
set compile=-classpath target/classes -s src -d target/classes src/be/beneterwan/gestiongare/logins/*.java
if %verbose%=="yes" (
    javac -verbose %compile%
) else (
    javac %compile%
)
echo Compilation du package applicgare...
set compile=-classpath "target/classes";%netbeansClasses% -s src -d target/classes src/be/beneterwan/gestiongare/applicgare/*.java
if %verbose%=="yes" (
    javac -verbose %compile%
) else (
    javac %compile%
)

if %jar%=="no" goto end
:jar
echo.
cd target/classes
set makeJar=../authenticate.jar be/beneterwan/gestiongare/authenticate/
echo Creation de authenticate.jar...
if %verbose%=="yes" (
    jar cvf %makeJar%
) else (
    jar cf %makeJar%
)
set makeJar=../gestiongare.jar ../../manifest.mf be.beneterwan.gestiongare.logins.FenLogin be/beneterwan/gestiongare/logins/
echo Creation de gestiongare.jar...
if %verbose%=="yes" (
    jar cvfme %makeJar%
) else (
    jar cfme %makeJar%
)
set makeJar=../Applic_Gare.jar ../../manifest.mf be.beneterwan.gestiongare.applicgare.ApplicGare be/beneterwan/gestiongare/applicgare/
echo Creation de Applic_Gare.jar...
if %verbose%=="yes" (
    jar cvfme %makeJar%
) else (
    jar cfme %makeJar%
)
cd ../..

:end
echo.

:eof

popd
