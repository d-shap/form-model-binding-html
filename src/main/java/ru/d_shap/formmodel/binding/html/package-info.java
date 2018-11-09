///////////////////////////////////////////////////////////////////////////////////////////////////
// Form model html binding is a form model binding implementation for HTML.
// Copyright (C) 2018 Dmitry Shapovalov.
//
// This file is part of Form model html binding.
//
// Form model html binding is free software: you can redistribute it and/or modify
// it under the terms of the GNU Lesser General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// Form model html binding is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public License
// along with this program. If not, see <http://www.gnu.org/licenses/>.
///////////////////////////////////////////////////////////////////////////////////////////////////
/**
 * <p>
 * Form-model binding implementation for HTML.
 * </p>
 * <p>
 * Form model library mediates between the source HTML and the application and encapsulates the complexity of
 * the source HTML. The essential parts of the HTML are described in the form definition XML. Then this
 * form definition is binded with the source HTML and the result of this binding is a binded form. Binded
 * elements can be obtained by the application from this binded form.
 * </p>
 * <p>
 * For example, suppose the following source HTML:
 * </p>
 * <pre>
 * &lt;html&gt;
 *     &lt;head&gt;
 *         &lt;title&gt;Test page&lt;/title&gt;
 *     &lt;/head&gt;
 *     &lt;body&gt;
 *         &lt;p&gt;Some text.&lt;/p&gt;
 *         &lt;p&gt;Some other text.&lt;/p&gt;
 *         &lt;p&gt;Some more text.&lt;/p&gt;
 *     &lt;/body&gt;
 * &lt;/html&gt;
 * </pre>
 * <p>
 * To extract the <i>&lt;p&gt;</i> tags text, suppose the following form definition:
 * </p>
 * <pre>
 * &lt;?xml version='1.0'?&gt;
 * &lt;ns1:form id='p-extractor' xmlns:ns1='http://d-shap.ru/schema/form-model/1.0'&gt;
 *     &lt;ns1:element id='p-element' lookup='p' type='required+'/&gt;
 * &lt;/ns1:form&gt;
 * </pre>
 * <p>
 * Here we define that the form contains one or more <i>&lt;p&gt;</i> tags.
 * </p>
 * <p>The following code implements the binding:</p>
 * <pre>{@code
 * // Load form definitions
 * FormDefinitions formDefinitions = new FormDefinitions();
 * FormDefinitionsLoader formDefinitionsLoader = new FormXmlDefinitionsFileLoader(new File("file with the form definition"));
 * formDefinitionsLoader.load(formDefinitions);
 *
 * // Bind the HTML
 * HtmlFormBinder formBinder = new HtmlFormBinder(formDefinitions);
 * Document document = formBinder.bindHtml(html, "p-extractor");
 *
 * // Get the binded elements and text
 * List<Element> elements = formBinder.getElementsWithId(document, "p-element");
 * List<HtmlBindedElement> bindedElements = formBinder.getBindedElements(elements);
 * for (HtmlBindedElement bindedElement: bindedElements) {
 *     bindedElement.getOwnText();
 * }
 * }</pre>
 * <p>
 * The application's code does not depend on the source HTML. All the changes in the source HTML affect
 * only on the form definition XML, so no recompilation is needed. Also there is no need to change the
 * code if the binding rules change.
 * </p>
 * <p>
 * For example, if <i>&lt;h1&gt;</i> and <i>&lt;h2&gt;</i> tags are added to the source HTML and we need to extract
 * the text from this new tags too. Then we need to change only the form definition XML as following:
 * </p>
 * <pre>
 * &lt;?xml version='1.0'?&gt;
 * &lt;ns1:form id='p-extractor' xmlns:ns1='http://d-shap.ru/schema/form-model/1.0'&gt;
 *     &lt;ns1:element id='p-element' lookup='h1' type='required+'/&gt;
 *     &lt;ns1:element id='p-element' lookup='h2' type='required+'/&gt;
 *     &lt;ns1:element id='p-element' lookup='p' type='required+'/&gt;
 * &lt;/ns1:form&gt;
 * </pre>
 * <p>
 * Or as following:
 * </p>
 * <pre>
 * &lt;?xml version='1.0'?&gt;
 * &lt;ns1:form id='p-extractor' xmlns:ns1='http://d-shap.ru/schema/form-model/1.0'&gt;
 *     &lt;ns1:element id='p-element' lookup='h1, h2, p' type='required+'/&gt;
 * &lt;/ns1:form&gt;
 * </pre>
 * <p>
 * If, for example, we need to extract the text not from all <i>&lt;p&gt;</i> tags, but from <i>&lt;p&gt;</i>
 * tags with class <i>pclass</i>. Then we need to change the form definition XML as following:
 * </p>
 * <pre>
 * &lt;?xml version='1.0'?&gt;
 * &lt;ns1:form id='p-extractor' xmlns:ns1='http://d-shap.ru/schema/form-model/1.0'&gt;
 *     &lt;ns1:element id='p-element' lookup='p.pclass' type='required+'/&gt;
 * &lt;/ns1:form&gt;
 * </pre>
 */
package ru.d_shap.formmodel.binding.html;
