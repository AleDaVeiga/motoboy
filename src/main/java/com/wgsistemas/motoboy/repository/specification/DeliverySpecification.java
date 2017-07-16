package com.wgsistemas.motoboy.repository.specification;

final class DeliverySpecification {
	private DeliverySpecification() {
	}

	// Mapear processor no Maven ->
	// org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor
	// static Specification<Delivery>
	// deliveryFromOrDeliveryToContainsIgnoreCase(String searchTerm) {
	// return (root, query, cb) -> {
	// Create query here
	// String containsLikePattern = getContainsLikePattern(searchTerm);
	// return cb.or(
	// cb.like(cb.lower(root.<String>get(Delivery_.deliveryFrom)),
	// containsLikePattern),
	// cb.like(cb.lower(root.<String>get(Delivery_.deliveryTo)),
	// containsLikePattern)
	// );
	// };
	// }

	// private static String getContainsLikePattern(String searchTerm) {
	// if (searchTerm == null || searchTerm.isEmpty()) {
	// return "%";
	// } else {
	// return "%" + searchTerm.toLowerCase() + "%";
	// }
	// }
}
