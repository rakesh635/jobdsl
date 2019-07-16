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
            cpsScm {
                scm {
                    git {
                        remote { url(repo) }
                        branches('master', '**/feature*')
                        scriptPath('misc/Jenkinsfile.v2')
                        extensions { }  // required as otherwise it may try to tag the repo, which you may not want
                    }
                }
            }
        }
    }
}
