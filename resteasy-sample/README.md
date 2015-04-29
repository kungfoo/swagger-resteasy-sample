# Sample REST application running resteasy and JBoss AS 7

## How to run this

    mvn clean && rake && mvn jboss-as:run

Then navigate to

    http://localhost:8080/resteasy-sample/rest/pets

If you can't run the rakefile because you

a) don't have rake
b) don't have unzip
c) don't have wget

You can do the following:

- download Jboss AS 7.1.1
- download resteasy complete distribution 3.0.9
- unpack jboss to `vendor/jboss_as`
- monkey path resteasy as described here: https://docs.jboss.org/resteasy/docs/3.0.9.Final/userguide/html/Installation_Configuration.html#upgrading-as7

Then run

    mvn clean jboss-as:run

Once you get past the custom JBoss install this should be fairly easy.