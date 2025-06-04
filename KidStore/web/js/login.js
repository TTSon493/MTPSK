/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function toggleResetPswd(e){
    e.preventDefault();
    $('#logreg-forms .form-signin').toggle()
    $('#logreg-forms .form-reset').toggle()
}

function toggleSignUp(e){
    e.preventDefault();
    $('#logreg-forms .form-signin').toggle();
    $('#logreg-forms .form-signup').toggle();
}

$(()=>{
    $('#logreg-forms #forgot_pswd').click(toggleResetPswd);
    $('#logreg-forms #cancel_reset').click(toggleResetPswd);
    $('#logreg-forms #btn-signup').click(toggleSignUp);
    $('#logreg-forms #cancel_signup').click(toggleSignUp);
})


