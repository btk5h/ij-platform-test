package com.example.ijplatformtest

import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.ProjectActivity
import git4idea.repo.GitRepositoryManager
import icons.RubyIcons

class SampleProjectActivity : ProjectActivity {
    override suspend fun execute(project: Project) {
        GitRepositoryManager.getInstance(project)
        RubyIcons.Ruby.Ruby
    }
}
