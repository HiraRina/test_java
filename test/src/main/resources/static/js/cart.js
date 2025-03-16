
//カート内削除
function deleteCartItem(cartId){
	if(!confirm('この商品を削除しますか？')){
		return;
	}
	
	fetch(`/test/api/cartdetail/${cartId}`,{
		method: 'DELETE'
	})
	.then(response => {
		if(response.ok){
			alert('削除しました');
			location.reload();
		} else {
			alert(message);
		}
	})
	.catch(error => {
		console.error('通信エラー:', error);
		alert('通信エラーが発生しました');
	})
}

//カート内の商品数変更
document.addEventListener('DOMContentLoaded', () =>{
	const selects = document.querySelectorAll('.quantity-select');
	
	selects.forEach(select => {
		select.addEventListener('change', (event) =>{
			const cartId = select.getAttribute('data-cart-id');
			const newQuantity = event.target.value;
			console.log(`cartId: ${cartId}, newQuantity: ${newQuantity}`);
			
			updateCartQuantity(cartId,newQuantity);
		});
	});
});

function updateCartQuantity(cartId,newQuantity){
	fetch(`/test/api/cartdetail/${cartId}`,{
		method: "PUT",
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify({ quantity: newQuantity })
	})
	.then(response => {
		if(!response.ok){
			 throw new Error(`HTTPエラー: ${response.status}`);
		}
		return response.json();
	})
	.then(data => {
		// ここが実際に受け取ったJSONオブジェクト
		console.log('レスポンスデータ:', data);

		// null/undefinedチェック
		if (!data) {
		  console.error("レスポンスが空だよ！", data);
		  throw new Error("レスポンスデータが取得できません");
		}

		//各商品の小計、税込み価格の更新
		document.getElementById(`subtotal-${cartId}`).textContent = `${data.subtotal}円`;
		document.getElementById(`taxSubtotal-${cartId}`).textContent = `${data.taxSubtotal}円`;
		//カート内の合計、数量更新
		document.getElementById('totalPrice').textContent = `${data.totalPrice}円`;
		document.getElementById('totalQuantity').textContent = `${data.totalQuantity}個`;
		document.getElementById('totalTaxPrice').textContent = `${data.totalTaxPrice}円`;
		console.log('更新成功')
		
		})
		.catch(error => {
			console.error('通信エラー:',error);
			alert('数量変更に失敗しました')
		});
	};
		




