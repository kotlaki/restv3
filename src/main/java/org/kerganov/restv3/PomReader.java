package org.kerganov.restv3;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

import java.io.FileReader;
import java.io.IOException;

/*
    вытаскиваем информацию из pom.xml MavenXpp3Reader
*/

public class PomReader {
    public static void main(String[] args) throws IOException, XmlPullParserException {
        MavenXpp3Reader reader = new MavenXpp3Reader();
        Model model = reader.read(new FileReader("pom.xml"));
        System.out.println(model.getArtifactId());
        System.out.println(model.getVersion());
//        for(Dependency o : model.getDependencies()) {
//            System.out.println(o.getArtifactId());
//        }
    }
}
