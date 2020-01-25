// package com.houses.assemblers;

// import com.houses.api.HousesController;
// import com.houses.models.House;

// import org.springframework.hateoas.EntityModel;
// import org.springframework.hateoas.server.RepresentationModelAssembler;
// import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
// import org.springframework.stereotype.Component;

// @Component
// class HouseEntityAssembler implements RepresentationModelAssembler<HouseEntityAssembler, EntityModel<House>> {

//   @Override
//   public EntityModel<House> toModel(HouseEntityAssembler house) {
//     // Assemble links to be returned in JSON responses.
//     EntityModel<House> houseEntity = new EntityModel<>(house,
//       linkTo(methodOn(HousesController.class).getHouse(.withSelfRel(),
//       linkTo(methodOn(HousesController.class).getHouses()).withRel("houses"));

//     return houseEntity;
//   }

//   @Override
//   public EntityModel<House> toModel(House house) {

//     // Unconditional links to single-item resource and aggregate root

//     Resource<Order> orderResource = new Resource<>(order,
//       linkTo(methodOn(OrderController.class).one(order.getId())).withSelfRel(),
//       linkTo(methodOn(OrderController.class).all()).withRel("orders")
//     );

//     // Conditional links based on state of the order

//     if (order.getStatus() == Status.IN_PROGRESS) {
//       orderResource.add(
//         linkTo(methodOn(OrderController.class)
//           .cancel(order.getId())).withRel("cancel"));
//       orderResource.add(
//         linkTo(methodOn(OrderController.class)
//           .complete(order.getId())).withRel("complete"));
//     }

//     return orderResource;
//   }
// }