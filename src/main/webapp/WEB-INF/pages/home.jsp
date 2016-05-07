<!doctype html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<html>
  <head>
    <title>Curator</title>
    <link rel="stylesheet" href="/pivotal-ui-5.0.2/release/pui-v3.0.0-alpha.1/pivotal-ui.css">
    <link rel="stylesheet" href="/css/custom.css">
    <script src="/pivotal-ui-5.0.2/release/pui-v3.0.0-alpha.1/pivotal-ui.js"></script>
  </head>
  <body>
    <header class="main">
           <div class="pivotal-logo mrxl">
                    <svg version="1.1" id="Layer_1" data-svgreactloader="[[&quot;http://www.w3.org/2000/svg&quot;,&quot;xlink&quot;,&quot;http://www.w3.org/1999/xlink&quot;]]" x="0px" y="0px" viewBox="0 0 38.7 56.7" xml:space="preserve" xlink="http://www.w3.org/1999/xlink"><g><path data-svgreactloader="[[null,&quot;style&quot;,&quot;fill:#FFFFFF;&quot;]]" d="M15.5,0L0,0v56.7h9.6V8.6h4.8c1.1,0,2.2,0,3.1,0.1c7.9,0.2,11.8,2.6,11.8,8.7c0,0.2,0,0.4,0,0.7
                    		c0,5.7-3.1,9.4-11.7,9.4c-0.8,0-2.1-0.2-2.1-0.2V35c0,0,1.2,0,2.1,0c12.3,0,21-4.8,21-17c0-0.2,0-0.5,0-0.7
                    		C38.7,4.7,29.2,0,15.5,0z" style="fill:#FFFFFF;"></path></g></svg>


            <div class="header-content">
                <section class="header-title">
                    <div>
                        <span class="h3 em-high type-neutral-11">Curator</span>
                    </div>
                </section>
            </div>
    </header>


    <div class="pane bg-dark-3">
      <div class="container">

      <h1 class="type-neutral-11">Cloud Foundry Information</h1>
       <div class="panel panel-simple bg-cloud">
            <div class="panel-body">
                <ul class="list-group-inverse">
                  <li class="list-group-item">
                   <h2 class="type-brand-5">CF CLI</h2>
                    <b>Build:</b> ${cloudfoundryInfo.build}  <b>API Version:</b> ${cloudfoundryInfo.api_version} <b>Min CLI Version:</b> ${cloudfoundryInfo.min_cli_version}
                  </li>
                  <li class="list-group-item">
                   <h2 class="type-brand-5">Ops Manager</h2>
                    <c:forEach var="product" items="${opsManInfo.products}" >
                     <b>Name:</b> ${product.installation_name} <b>Version:</b> ${product.product_version} <br/>
                    </c:forEach>
                  </li>
                </ul>
            </div>
       </div>



        <h1 class="type-neutral-11">Applications</h1>

        <div class="panel panel-simple bg-cloud">
          <div class="panel-body">
            <div class="table-scrollable table-scrollable-sm">
              <div class="table-scrollable-header">
                <table class="table table-data table-light table-striped">
                  <thead>
                    <tr>
                      <th width="20%">Name</th>
                      <th width="15%">Status</th>
                      <th width="10%">CPU</th>
                      <th width="20%">Memory</th>
                      <th width="20%">Disk</th>
                      <th width="15%">Uptime (s)</th>
                    </tr>
                  </thead>
                </table>
              </div>
              <div class="table-scrollable-body">
                <table class="table table-data table-light table-striped">
                  <tbody>
                   <c:forEach var="appCatalogItem" items="${apps}">
                    <tr>
                      <td width="20%"> ${appCatalogItem.name}:${appCatalogItem.instanceIndex} </td>
                      <td width="15%"> ${appCatalogItem.state} </td>
                      <td width="10%"> <fmt:formatNumber value="${appCatalogItem.cpuPercentage}" type="number" maxFractionDigits="2" /></td>
                      <td width="20%"> ${appCatalogItem.memoryBytes}</td>
                      <td width="20%"> ${appCatalogItem.diskBytes}</td>
                      <td width="15%"> ${appCatalogItem.uptime} </td>
                    </tr>
                    </c:forEach>
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </div>

        <h1 class="type-neutral-11">Events</h1>

                <div class="panel panel-simple bg-cloud">
                  <div class="panel-body">
                    <div class="table-scrollable table-scrollable-sm">
                      <div class="table-scrollable-header">
                        <table class="table table-data table-light table-striped">
                          <thead>
                            <tr>
                              <th width="30%">App Name</th>
                              <th width="70%">Description</th>
                            </tr>
                          </thead>
                        </table>
                      </div>
                      <div class="table-scrollable-body">
                        <table class="table table-data table-light table-striped">
                          <tbody>
                           <c:forEach var="event" items="${events}">
                            <tr>
                              <td width="30%"> ${event.appId}</td>
                              <td width="70%"> ${event.description} </td>
                            </tr>
                            </c:forEach>
                          </tbody>
                        </table>
                      </div>
                    </div>
                  </div>
                </div>

      </div>
    </div>
  </body>
</html>