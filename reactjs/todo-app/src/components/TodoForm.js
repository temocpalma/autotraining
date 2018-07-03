import React, {Component} from 'react';

export default class TodoForm extends Component {
  constructor() {
    super();
    this.state = {
      todo:''
    }
  }

  updateTodo (evt) {
    this.setState({
      todo: evt.target.value
    });
  }

  addTodo (evt) {
    evt.preventDefault();
    this.props.addTodo(this.state.todo);
    this.setState({
      todo: ''
    });
  }

  render() {
    const {todo} = this.state;
    return (
      <form onSubmit={e => this.addTodo(e)}>
        <div className="row">
          <div className="col">
            <label htmlFor="todo" className="sr-only">ToDo</label>
            <input
              type="text"
              className="form-control"
              id="todo"
              value={todo}
              onChange={this.updateTodo.bind(this)}
            />
          </div>
          <div className="row">
            <button type="submit" className="btn btn-primary mb-2">
              Añadir
            </button>
          </div>
        </div>
      </form>
    )
  }
}
