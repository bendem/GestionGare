@echo off

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
IF EXIST target\*.jar del /S /Q /F target\*.jar
IF EXIST target\classes rmdir /s /q target\classes
IF NOT EXIST target\classes mkdir target\classes

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
set makeJar=../gestiongare.jar ../../manifest.mf be/beneterwan/gestiongare/logins/
echo Creation de gestiongare.jar...
if %verbose%=="yes" (
    jar cvfm %makeJar%
) else (
    jar cfm %makeJar%
)
cd ../..

:end
echo.

:eof
