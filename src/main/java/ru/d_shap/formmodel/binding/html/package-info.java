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
 * Form model library mediates between the HTML and the application and encapsulates the complexity of the HTML.
 * The essential parts of the HTML are described in the form definition XML. Then this form definition is
 * binded with the HTML source and the result of this binding is a binded form. Binded elements can be obtained by
 * the application from this binded form.
 * </p>
 * <p>
 * For example, suppose the following HTML:
 * </p>
 * <pre>{@code
 * <html>
 *     <head>
 *         <title>Test page</title>
 *     </head>
 *     <body>
 *         <p>Some text.</p>
 *         <p>Some other text.</p>
 *         <p>Some more text.</p>
 *     </body>
 * </html>
 * }</pre>
 * <p>
 * To extract &lt;p&gt; elements text, suppose the following form definition:
 * </p>
 * <pre>{@code
 * <?xml version='1.0'?>
 *     <ns1:form id='p-extractor' xmlns:ns1='http://d-shap.ru/schema/form-model/1.0'>
 *         <ns1:element id='p-element' lookup='p' type='required+'/>
 *     </ns1:form>
 * }</pre>
 * <p>
 * Here we define that the form contains one or more &lt;p&gt; elements.
 * </p>
 * <p>The following code implements binding:</p>
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
 */
package ru.d_shap.formmodel.binding.html;
