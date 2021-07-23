import React from 'react'

export const LogoutOption: React.FunctionComponent = () => {
    return (
        <div>
            <a type="button" className="btn btn-outline-danger" href="/logout">
                Log out
            </a>
        </div>
    )
}