<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="panel-footer text-center">
	<nav aria-label="Navega��o de p�ginas">
		<ul class="pagination pagination-sm">
			<li class="page-item ${page.firstPage ? 'disabled' : ''}">
				<a class="page-link" href="${param.paginationUrl}&page=0&size=${page.size}" aria-label="Anterior">
					<span aria-hidden="true">&laquo;</span>
					<span class="sr-only">Anterior</span>
				</a>
			</li>
			<c:forEach var="item" items="${page.items}">
				<li class="page-item ${item.current ? 'active' : ''}">
					<c:choose>
						<c:when test="${item.current}">
							<span class="page-link"> ${item.number} <span
								class="sr-only">(atual)</span>
							</span>
						</c:when>
						<c:otherwise>
							<a class="page-link"
								href="${param.paginationUrl}&page=${item.number - 1}&size=${page.size}">${item.number}</a>
						</c:otherwise>
					</c:choose></li>
			</c:forEach>
			<li class="page-item ${page.lastPage ? 'disabled' : ''}">
				<a class="page-link" href="${param.paginationUrl}&page=${page.totalPages - 1}&size=${page.size}" aria-label="Pr�ximo">
					<span aria-hidden="true">&raquo;</span>
					<span class="sr-only">Pr�ximo</span>
				</a>
			</li>
		</ul>
	</nav>
</div>