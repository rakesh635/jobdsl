if("$LANG" == "PHP" || "$LANG" == "php")
{
    
}
elseif("$LANG" == "Java" || "$LANG" == "JAVA" || "$LANG" == "java")
{
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
            }
        }
    }
}
