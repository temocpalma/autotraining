import React from 'react';
import { Container, Table, Button } from 'react-bootstrap';
import { removeFromCart } from '../actionCreators';
import { connect } from 'react-redux';

const styles = {
  footer: {
    fontWeight: 'bold'
  }
}

const ShoppingCart = ({ cart, removeFromCart }) => {
  return (
    <Container>
      <h4>Shopping Cart</h4>
      <Table fill="true">
        <tbody>
          {cart.map(product =>
            <tr key={product.id}>
              <td>{product.name}</td>
              <td className="text-right">{product.price}</td>
              <td className="text-right">
                <Button as="input"
                  bssize="xsmall" 
                  variant="danger" 
                  onClick={() => removeFromCart(product)}
                  value="X"/>
              </td>
            </tr>
          )}
        </tbody>
        <tfoot>
          <tr>
            <td colSpan="4" style={styles.footer}>
              Total: $ {cart.reduce((sum, product) => sum + product.price, 0)}
            </td>
          </tr>
        </tfoot>
      </Table>
    </Container>
  );
}

const mapStateToProps = state => {
  return {
    cart: state.cart
  };
};

const mapDispatchToProps = dispatch => {
  return {
    removeFromCart(product) {
      dispatch(removeFromCart(product));
    }
  };
};

export default connect(mapStateToProps, mapDispatchToProps) (ShoppingCart);