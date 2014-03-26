@echo off

pushd %cd%

REM +----------------------------------------------------------
REM | Change these values only!
REM +----------------------------------------------------------
set netbeansClasses=/Dropbox/école/2013-2014/Java/GestionGare/lib/swing-layout/swing-layout-1.0.4.jar
set sourceFolder=src/main/java
set resourceFolder=src/main/resources
set buildFolder=target
set mainPackage=be.beneterwan.gestiongare

REM -----------------------------------------------------------
set absolutePath=%~dp0
set absolutePath=%absolutePath:\=/%
set absolutePath=%absolutePath:~2%
set mainPackageDir=%mainPackage:.=/%
set fullMainPackageDir=%sourceFolder%/%mainPackageDir%

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
mkdir classes
cd ..
IF NOT EXIST target\classes GOTO eof


:compile
echo.
echo Compilation du package authenticate...
set compile=-encoding utf-8 -s %sourceFolder% -d %buildFolder%/classes %fullMainPackageDir%/authenticate/*.java
if %verbose%=="yes" (
    javac -verbose %compile%
) else (
    javac %compile%
)
echo Compilation du package logger...
set compile=-encoding utf-8 -s %sourceFolder% -d %buildFolder%/classes %fullMainPackageDir%/logger/*.java
if %verbose%=="yes" (
    javac -verbose %compile%
) else (
    javac %compile%
)
echo Compilation du package logins...
set compile=-encoding utf-8 -classpath %buildFolder%/classes -s %sourceFolder% -d %buildFolder%/classes %fullMainPackageDir%/logins/*.java
if %verbose%=="yes" (
    javac -verbose %compile%
) else (
    javac %compile%
)
echo Compilation du package applicgare...
set compile=-encoding utf-8 -classpath "%buildFolder%/classes";%netbeansClasses% -s %sourceFolder% -d %buildFolder%/classes %fullMainPackageDir%/applicgare/*.java
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
set makeJar=../logger.jar %mainPackageDir%/logger/
echo Creation de logger.jar...
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
xcopy %absolutePath:/=\%%resourceFolder:/=\% resources\ /S /Y
set makeJar=../Applic_Gare.jar ../../manifest.mf %mainPackage%.applicgare.ApplicGare %mainPackageDir%/applicgare/ resources/train.jpg
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
