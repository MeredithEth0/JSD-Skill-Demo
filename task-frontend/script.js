let allTasks = [];

window.addEventListener('load', loadTasks);

document.getElementById('taskForm').addEventListener('submit', async function(e) {
    e.preventDefault();
    
    // Get form values
    let formData = {
        title: document.getElementById('title').value.trim(),
        description: document.getElementById('description').value.trim() || null,
        status: document.getElementById('status').value,
        dueDateTime: document.getElementById('dueDateTime').value
    };
    
    let message = document.getElementById('message');
    message.textContent = 'Creating task...';
    
    try {
        // Send to backend
        let response = await fetch('http://localhost:8080/tasks', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(formData)
        });
        
        if (!response.ok) {
            throw new Error('Create failed: ' + response.status);
        }
        
        // Clear form and show success
        document.getElementById('taskForm').reset();
        message.textContent = 'Task created!';
        message.className = 'success';
        
        // Refresh list
        loadTasks();
        
    } catch (error) {
        message.textContent = error.message;
        message.className = 'error';
    }
});

async function loadTasks() {
    let container = document.getElementById('tasksContainer');
    container.textContent = 'Loading...';
    
    try {
        let response = await fetch('http://localhost:8080/tasks');
        
        if (!response.ok) {
            throw new Error('Load failed: ' + response.status);
        }
        
        allTasks = await response.json();
        renderTasks();
        
    } catch (error) {
        container.innerHTML = '<p style="color:red">Failed to load tasks</p>';
    }
}

function renderTasks() {
    let container = document.getElementById('tasksContainer');
    
    if (allTasks.length === 0) {
        container.innerHTML = '<p>No tasks yet</p>';
        return;
    }
    
    container.innerHTML = allTasks.map(function(task) {
        return `
            <div class="task-card">
                <h3>${task.title}</h3>
                <p>ID: ${task.id}</p>
                <p>Status: <span class="status-${task.status.toLowerCase()}">${task.status}</span></p>
                ${task.description ? '<p>Description: ' + task.description + '</p>' : ''}
                <p>Due: ${new Date(task.dueDateTime).toLocaleString()}</p>
                <p>Created: ${new Date(task.createdAt).toLocaleString()}</p>
            </div>
        `;
    }).join('');
}

document.getElementById('refreshList').addEventListener('click', loadTasks);
