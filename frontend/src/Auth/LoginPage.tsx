function LoginPage() {

  const handleFormSubmit = async () => {

  }
  return (
    <div className="flex justify-center items-center h-screen w-screen">
      <div>
      </div>

      <form action={handleFormSubmit} className="border border-black h-[750px] w-[600px] rounded-[50px] flex justify-center items-center">

        <label htmlFor="">User</label>
        <input type="text" name="" id="" className="border border-black" />

        <label htmlFor="">Password</label>
        <input type="text" name="" id="" className="border border-black" />

      </form>
    </div>
  )
}

export default LoginPage
