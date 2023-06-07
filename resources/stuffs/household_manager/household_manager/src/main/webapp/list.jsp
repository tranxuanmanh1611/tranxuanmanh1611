<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 04/05/2023
  Time: 11:14 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    <title>Household Manager</title>
</head>
<body>


<c:choose>
    <c:when test="${requestScope['alert']!=null}">
        <h3>${requestScope['alert']}</h3>
    </c:when>
    <c:otherwise>
        <h3>${requestScope['error']}</h3>
    </c:otherwise>
</c:choose>
    <table>

        <tr>
            <td>
                <form action="">
                    <select name="pageOrder">
                        <c:forEach begin="0" end="${requestScope['numberOfPage']}" varStatus="loop">
                            <c:choose>
                                <c:when test="${requestScope['pageOrder']==loop.count}">
                                    <option value="${loop.count}" selected>Page ${loop.count}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${loop.count}">Page ${loop.count}</option>
                                </c:otherwise>
                            </c:choose>

                        </c:forEach>
                    </select>
                    <button>Go</button>
                </form>
            </td>
        </tr>

        <tr>
            <th>Order</th>
            <th>Household ID</th>
            <th>Owner</th>
            <th>Member</th>
            <th>Create Date</th>
            <th>Address</th>
            <th>Operation</th>
        </tr>


        <c:forEach items="${requestScope['houseList']}" var="house" varStatus="loop">
            <c:choose>
                <c:when test="${house.getId()==requestScope['edit']}">
                    <form method="post" action="">
                        <tr>
                            <td>${loop.count+(requestScope['pageOrder']-1)*3}</td>
                            <td>${house.getId()}</td>
                            <td><input name="owner" type="text" placeholder="old owner: ${house.getOwner()}" required></td>
                            <td>${house.getMember()}</td>
                            <td><input name="createDate" type="text" placeholder="old date: ${house.getCreateDate()}" required></td>
                            <td><input name="address" type="text" placeholder="old address: ${house.getAddress()}" required></td>
                            <td>
                                <button type="submit" onclick="return editConfirm()">Edit</button>
                    </form>
                                <a href="house" onclick="return cancelConfirm()"><button>Cancel</button></a>
                            </td>
                        </tr>

                </c:when>


                <c:otherwise>
                    <tr>
                        <td>${loop.count+(requestScope['pageOrder']-1)*3}</td>
                        <td>${house.getId()}</td>
                        <td>${house.getOwner()}</td>
                        <td>${house.getMember()}</td>
                        <td>${house.getCreateDate()}</td>
                        <td>${house.getAddress()}</td>
                        <td>
                            <a href="house?action=edit&id=${house.getId()}&pageOrder=${requestScope['pageOrder']}"><button>Edit</button></a>
                            <button onclick="showMemberInfo('${house.getMemberInfo()}')" data-bs-toggle="modal" data-bs-target="#exampleModal">Members detail</button>
                        </td>
                    </tr>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </table>

<!-- Button trigger modal -->
<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
    Launch demo modal
</button>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabel">Household Members</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <p id="memberInfo"></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

    <script>
function showMemberInfo(memberInfo){
document.getElementById('memberInfo').innerHTML=memberInfo;
}

          function editConfirm() {
            return window.confirm("Are you sure you want to edit this?")
        }
        function cancelConfirm() {
            return window.confirm("Are you sure you want to cancel?")
        }
    </script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
</body>
</html>
