@echo off

pushd %cd%

set netbeansClasses=/Dropbox/école/2013-2014/Java/GestionGare/lib/swing-layout/swing-layout-1.0.4.jar
set sourceFolder=src/main
set buildFolder=target
set mainPackage=be.beneterwan.gestiongare
set mainPackageDir=be/beneterwan/gestiongare
set mainPackageDir=%mainPackage:.=/%

set clean="yes"
set jar="yes"
set verbose="no"

rem parsage des arguments
for %%a in (%*) do (
    if [%%a]==[-noclean] set clean="no"
    if [%%a]==[-nojar] set jar="no"
    if [%%a]==[-v] set verbose="yes"
    if [%%a]==[-h] (
        echo.
        echo Available parameters : -noclean, -nojar, -v, -h
        echo.
        echo -noclean : Don't clean the %buildFolder% directory before building
        echo -nojar   : Don't create jar files
        echo -v       : Verbose debug infos
        echo -h       : Display this help
        goto eof
    )
)

if %clean%=="no" goto compile

echo.
echo Nettoyage...
cd %buildFolder%
IF EXIST *.jar del /Q /F *.jar
IF EXIST classes rmdir /s /q classes
IF NOT EXIST classes\ mkdir classes
cd ..

:compile
echo.
echo Compilation du package authenticate...
set compile=-s src -d %buildFolder%/classes %sourceFolder%/%mainPackageDir%/authenticate/*.java
if %verbose%=="yes" (
    javac -verbose %compile%
) else (
    javac %compile%
)
echo Compilation du package logins...
set compile=-classpath %buildFolder%/classes -s %sourceFolder% -d %buildFolder%/classes %sourceFolder%/%mainPackageDir%/logins/*.java
if %verbose%=="yes" (
    javac -verbose %compile%
) else (
    javac %compile%
)
echo Compilation du package applicgare...
set compile=-classpath "%buildFolder%/classes";%netbeansClasses% -s %sourceFolder% -d %buildFolder%/classes %sourceFolder%/%mainPackageDir%/applicgare/*.java
if %verbose%=="yes" (
    javac -verbose %compile%
) else (
    javac %compile%
)

if %jar%=="no" goto end
:jar
echo.
cd %buildFolder%/classes
set makeJar=../authenticate.jar %mainPackageDir%/authenticate/
echo Creation de authenticate.jar...
if %verbose%=="yes" (
    jar cvf %makeJar%
) else (
    jar cf %makeJar%
)
set makeJar=../gestiongare.jar ../../manifest.mf %mainPackage%.logins.FenLogin %mainPackageDir%/logins/
echo Creation de gestiongare.jar...
if %verbose%=="yes" (
    jar cvfme %makeJar%
) else (
    jar cfme %makeJar%
)
set makeJar=../Applic_Gare.jar ../../manifest.mf %mainPackage%.applicgare.ApplicGare %mainPackageDir%/applicgare/
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
