if("$LANG" == "PHP" || "$LANG" == "php")
{
    
}
else if("$LANG" == "Java" || "$LANG" == "JAVA" || "$LANG" == "java")
{
    def content = readFileFromWorkspace('java/Jenkinsfile')
    println(content);
    content.replaceAll('--REPOURL--',"$repourl")
    println(content);
    println("$repourl");
    pipelineJob("$pipelinename")
    {
        def repo = "$repourl"
        triggers {
            scm('H/5 * * * *')
        }
        description("Pipeline for $repo")
        definition {
            cps {
                script(content+'sdasdas')
                sandbox(true)
            }
        }
    }
}
