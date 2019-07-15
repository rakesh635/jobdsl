job('DSL-Tutorial-1-Test') {
    scm {
        git("$repourl")
    }
    triggers {
        scm('H/15 * * * *')
    }
    steps {
        maven('-e clean test')
    }
}
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
