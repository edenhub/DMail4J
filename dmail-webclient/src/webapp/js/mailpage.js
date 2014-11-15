/**
 * Created by adam on 2014/11/8.
 */

function createFold(event) {
    $('#createFoldModal').modal();
}

function doCreate(event){
    newName = $('#createFoldModal #createFold_newName').val();
    alert(newName)
    alert('ajax远程调用')
}

function renameFold(event) {

    $('#renameFoldModal').modal();
}

function doRename(event){
    newName = $('#renameFoldModal #renameFold_newName').val();
    alert(newName);
    alert('ajax远程调用');
}

function deleteFold(event) {
    $('#deleteFoldModal').modal();
}

function doDelete(event){
    alert('ajax远程调用');
}

function wrapChildNode(childList){
    for(var o in childList){
        alert(o.text)
        o.icon = "glyphicon glyphicon-tag";
        o.isPrivateChild = true;
    }

    return childList;
}

function getEmptyChildNode(strText,strId){
    var node = {
        text: strText,
        icon: "glyphicon glyphicon-tag",
        isPrivateChild: true,
        priId: strId
    };

    return node;
}

function buildTreeData(childList) {
    var defautlTree = [
        {
            text: '写信',
            icon: 'glyphicon glyphicon-pencil'
        },
        {
            text: "收信箱",
            icon: "glyphicon glyphicon-envelope",
            href: "1",
            badge: 3
        },
        {
            text: "已发送",
            icon: "glyphicon glyphicon-share-alt"
        },
        {
            text: "自定义文件夹",
            icon: "glyphicon glyphicon-tags",
            isPrivateParent: true,
            nodes: null
        },
        {
            text: "已删除",
            icon: "glyphicon glyphicon-trash"
        },
        {
            text: "垃圾邮件",
            icon: "glyphicon glyphicon-ban-circle"
        }
    ];

    //len = childList.length;
    //var tns = [len];
    //
    //
    //for(i=0;i<len;i++){
    //    node = getEmptyChildNode(childList[i].text,childList[i].priId);
    //    tns.push(node);
    //}

    //alert("length : "+childList.length);
    defautlTree[3].nodes = childList;
    //defautlTree[3].nodes = tns;
    return defautlTree;
}
