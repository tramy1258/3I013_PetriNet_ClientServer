javac -d bin -classpath "`pwd`/lib/fr.lip6.pnml.framework.3rdpartimports_2.2.14.jar:\
`pwd`/lib/fr.lip6.pnml.framework.ptnet_2.2.14.jar:\
`pwd`/lib/fr.lip6.pnml.framework.utils_2.2.14.jar:\
`pwd`/lib/org.eclipse.emf.common_2.17.0.v20190920-0401.jar:\
`pwd`/lib/org.eclipse.emf.ecore_2.20.0.v20190920-0401.jar:\
`pwd`/lib/org.slf4j.api_1.7.30.v20200204-2150.jar" ./src/*.java

echo "Usage: ./cliser_os_1 nb_clients nb_serveurs"
