# curator

to run locally:
./gradlew bootRun

to push to cf:
cf push curator -p build/libs/curator.war

cf set-env curator JAVA_OPTS '-Duaa.host=<uaa host> -Duaa.user=<uaa user> -Duaa.secret=<uaa secret> -Ddoppler.host=<doppler host> -Ddoppler.port=<doppler port> -Dapi.host=<cf hostname> -DopsMan.user=<opsman user> -DopsMan.password=<opsman password>'
