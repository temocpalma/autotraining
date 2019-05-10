import React from 'react';
import { render, mount, configure } from 'enzyme';
import Adapter from 'enzyme-adapter-react-16';
import configureStore from 'redux-mock-store';
import { Provider } from 'react-redux';
import ConnectedProductList from '../../components/main/ProductList';

configure({adapter: new Adapter()});

const mockStore = configureStore();

it ('renders no products when store is empty', () => {
  const store = mockStore({ products: [] });

  const wrapper = render(
    <Provider store={store}>
      <ConnectedProductList/>
    </Provider>
  );
  expect(wrapper.find('.product').length).toBe(0);
});

it ('renders products', () => {
  const store = mockStore({ 
    products: [
      {id:1, name:"Product 1", price:100, image:""}
    ] 
  });

  const wrapper = render(
    <Provider store={store}>
      <ConnectedProductList/>
    </Provider>
  );
  expect(wrapper.find('.product').length).toBe(1);
});

it ('adds a producto to shopping cart', () => {
  const store = mockStore({ 
    products: [
      {id:1, name:"Product 1", price:100, image:""}
    ] 
  });

  const wrapper = mount(
    <Provider store={store}>
      <ConnectedProductList/>
    </Provider>
  );
  wrapper.find('#product-1 button').simulate('click');
  const actions = store.getActions();

  expect(actions.length).toBe(1);
  expect(actions[0].type).toBe("ADD_TO_CART");
  expect(actions[0].product).not.toBeNull();
});