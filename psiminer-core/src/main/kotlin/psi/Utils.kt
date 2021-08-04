package psi

import com.intellij.psi.PsiElement
import com.intellij.psi.PsiRecursiveElementVisitor
import com.intellij.psi.util.PsiTreeUtil
import psi.nodeProperties.isHidden
import psi.nodeProperties.nodeType
import psi.nodeProperties.token

/**
 * Calculate size of PSI tree
 */
fun PsiElement.treeSize() =
    PsiTreeUtil.collectElementsOfType(this, PsiElement::class.java).size

class OrderPsiTreeVisitor : PsiRecursiveElementVisitor() {
    private val preOrder = hashMapOf<PsiElement, Int>()
    private val postOrder = hashMapOf<PsiElement, Int>()

    override fun visitElement(element: PsiElement) {
        if (element.isHidden) return
        preOrder[element] = preOrder.size
        super.visitElement(element)
        postOrder[element] = postOrder.size
    }

    fun preOrder() = preOrder.toList().sortedBy { it.second }.map { it.first }

    fun postOrder() = postOrder.toList().sortedBy { it.second }.map { it.first }
}

fun PsiElement.preOrder(): List<PsiElement> {
    val visitor = OrderPsiTreeVisitor()
    accept(visitor)
    return visitor.preOrder()
}

fun PsiElement.postOrder(): List<PsiElement> {
    val visitor = OrderPsiTreeVisitor()
    accept(visitor)
    return visitor.postOrder()
}

fun PsiElement.printTree(delimiter: String = "..", indentStep: Int = 2) {
    val depths = mutableMapOf<PsiElement, Int>()
    preOrder().forEach {
        val indent = depths[it.parent]?.plus(1) ?: 0
        depths[it] = indent
        println("${delimiter.repeat(indent * indentStep)} ${it.nodeType} -- ${it.token}")
    }
}
