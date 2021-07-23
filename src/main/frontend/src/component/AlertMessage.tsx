import React, {Component} from "react";

class AlertMessage extends Component<{ msg: any, classStyle: any }> {
    render() {
        let {msg} = this.props;
        return (
            <div className={"alert "+ this.props.classStyle}  role="alert">
                {msg}
            </div>
        );
    }
}

export default AlertMessage;