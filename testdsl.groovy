if("$LANG" == "PHP" || "$LANG" == "php")
{
    
}
else if("$LANG" == "Java" || "$LANG" == "JAVA" || "$LANG" == "java")
{
    def Jenkinsfile = readFileFromWorkspace('java/Jenkinsfile')
    Jenkinsfile = Jenkinsfile.replaceAll('--REPOURL--',"$repourl")
    Jenkinsfile = Jenkinsfile.replaceAll('--REPOBRANCH--',"$branch")
    
    pipelineJob("$pipelinename")
    {
        def repo = "$repourl"
        triggers {
            scm('H/5 * * * *')
        }
        description("Pipeline for $repo")
        definition {
            cps {
                script(Jenkinsfile)
                sandbox(true)
            }
        }
    }
}
