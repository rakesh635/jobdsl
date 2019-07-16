if("$LANG" == "PHP" || "$LANG" == "php")
{
    
}
else if("$LANG" == "Java" || "$LANG" == "JAVA" || "$LANG" == "java")
{
    def content = readFileFromWorkspace('java/Jenkinsfile')
    content.replaceAll('--REPOURL--',"$repourl")
    pipelineJob("$pipelinename")
    {
        def repo = "$repourl"
        triggers {
            scm('H/5 * * * *')
        }
        description("Pipeline for $repo")
        definition {
            cps {
                script(readFileFromWorkspace('java/Jenkinsfile'))
                sandbox(true)
            }
        }
    }
}
