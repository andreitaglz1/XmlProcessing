package com.baam.xmlprocessing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.xml.sax.InputSource
import java.io.StringReader
import javax.xml.parsers.DocumentBuilderFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Definir el archivo XML
        val xml = """
            <books>
                <book id="1">
                    <title>The Catcher in the Rye</title>
                    <author>J.D. Salinger</author>
                    <year>1951</year>
                </book>
                <book id="2">
                    <title>To Kill a Mockingbird</title>
                    <author>Harper Lee</author>
                    <year>1960</year>
                </book>
                <book id="3">
                    <title>Moby Dick</title>
                    <author>Herman Melville</author>
                    <year>1851</year>
                </book>
            </books>
        """.trimIndent()

        // Procesar el archivo XML
        val doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(InputSource(StringReader(xml)))
        doc.documentElement.normalize()

        // Obtener todos los nodos "book"
        val bookNodes = doc.getElementsByTagName("book")
        for (i in 0 until bookNodes.length) {
            val bookNode = bookNodes.item(i)
            if (bookNode.nodeType == Node.ELEMENT_NODE) {
                val bookElement = bookNode as Element
                val id = bookElement.getAttribute("id")
                val title = bookElement.getElementsByTagName("title").item(0).textContent
                val author = bookElement.getElementsByTagName("author").item(0).textContent
                val year = bookElement.getElementsByTagName("year").item(0).textContent
                Log.d("MainActivity", "Book $id: $title by $author ($year)")
            }
        }
    }
}
