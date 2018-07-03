import React, {Component} from 'react';
import TodoItem from './TodoItem';
import TodoForm from './TodoForm';
import TodoItemHead from './TodoItemHead';


export default class TodoList extends Component {
  constructor() {
    super();
    this.state = {
      todos: ['TODO 1']
    };
  }

  updateTodo (todo, index) {
    let todos = this.state.todos.slice();
    todos[index] = todo;
    this.setState({todos});
  }

  removeTodo (index) {
    let todos = this.state.todos.slice();
    todos.splice(index, 1); 
    this.setState({todos});
  }
   
  addTodo(todo) {
    if (todo) {
      this.setState({
        todos: [
          ...this.state.todos,
          todo
        ]
      });
    }
  }

  render() {
    const {todos} = this.state;

    if (!todos.length) {
      return (
        <div>
          <TodoForm addTodo={(todo) => this.addTodo(todo)}/>
          <hr/>
          <div className="alert alert-danger">No hay To-Dos</div>
        </div>
      )
    }

    return (
      <div className="col-md-12">
        <TodoForm addTodo={(todo) => this.addTodo(todo)}/>
        <hr/>

        <table className="table">
          <TodoItemHead/>
          <tbody>
            {
              todos.map((todo, index) => {
                return (
                  <TodoItem onUpdateTodo={(todoUpdated) => this.updateTodo(todoUpdated, index)}
                    onRemove={() => this.removeTodo(index)}
                    index={index}
                    key={index}
                  >
                    {todo}
                  </TodoItem>
                )
              })
            }
          </tbody>
        </table>
      </div>
    );
  }
}
