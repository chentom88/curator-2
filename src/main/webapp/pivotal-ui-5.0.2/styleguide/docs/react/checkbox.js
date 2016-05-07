/*doc
 ---
 title: Checkbox
 name: 00_form_checkbox_react
 parent: form_react
 ---

 <code class="pam">
 <i class="fa fa-download" alt="Install the Components">
 npm install pui-react-checkbox --save
 </i>
 </code>


 Require the subcomponent:

 ```
 var Checkbox = require('pui-react-checkbox').Checkbox;
 ```

 A Checkbox component renders a checkbox with a label. It accepts standard
 checkbox input properties (such as `placeholder`).

 ```react_example
 <Checkbox label="Label"/>
 ```

 A Checkbox component display a custom `errorMessage` when the `displayError` parameter is truthy.

 ```react_example
 <Checkbox
 label="Label!"
 labelClassName="hello"
 displayError={true}
 errorMessage="You must accept the terms and conditions!"
 inputClassName="hey"
 />
 ```
 */
