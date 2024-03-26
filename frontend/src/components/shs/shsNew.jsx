import React from 'react';

class SHS extends React.Component {
  state = {
    users: [
      { name: "Parent", userType: "Parent" },
      { name: "Child", userType: "Child" },
      { name: "Guest", userType: "Guest" },
      { name: "Stranger", userType: "Stranger" }
    ]
  };

  render() {
    return (
      <div className="flex w-full grid grid-cols-4 gap-4">
        {this.state.users.map((user, index) => (
          <div key={index} className="m-auto cursor-pointer">
            <div className="bg-black w-[128px] h-[128px] rounded-2xl">
              {/* You can put an image or icon here */}
            </div>
            <div className="text-center">{user.name}</div>
          </div>
        ))}
        {/* Extra div with a '+' sign */}
        <div className="m-auto cursor-pointer">
          <div className="flex items-center justify-center bg-black w-[128px] h-[128px] rounded-2xl text-white">
            <span className="text-4xl">+</span>
          </div>
          <div className="text-center">Add User Profile</div>
        </div>
      </div>
    );
  }
}

export default SHS;
