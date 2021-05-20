import com.intellij.psi.PsiClass
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiMethod

enum class GranularityLevel(val psiNodeClass: java.lang.Class<out PsiElement>) {
    File(PsiFile::class.java),
    Class(PsiClass::class.java),
    Method(PsiMethod::class.java)
}

enum class Dataset(val folderName: String) {
    Train("train"),
    Val("val"),
    Test("test")
}

enum class Language(val extensions: List<String>) {
    Java(listOf("java"))
}
