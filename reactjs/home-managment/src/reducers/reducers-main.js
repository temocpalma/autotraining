const products = (state=[], action) => {
  if (action.type === "LOAD_PRODUCTS") {
    return loadProducts(state, action.products);
  }
  return state;
};

const cart = (state=[], action) => {
  if (action.type === "ADD_TO_CART") {
    return addToCart(state, action.product);
  } else if (action.type === "REMOVE_FROM_CART") {
    return removeFromCart(state, action.product);
  }  
  return state;
};

const loadProducts = (state, products) => {
  return products;
};

const addToCart = (state, product) => {
  return state.concat(product);
};

const removeFromCart = (state, product) => {
  return state.filter(prod => prod.id !== product.id);
};

export { products, cart };